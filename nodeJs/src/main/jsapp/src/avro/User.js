/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * @author Ji Kim
 */
'use strict';

import AvroJson from './avrojson';
import AbstractAvro from './AbstractAvro';
import UserId from './UserId';
import {TOPICS, API} from '../common/PubSub';
import Common from '../common/Common';

const FORM_MAPPER = Symbol('FORM_MAPPER');
const U_GEOLOCATION_OPTS = {'timeout': 30000, 'maximumAge': 30000};
const GEOLOCATION_NOTIFICATION_CHANGE_THRESHOLD = 2;

class User extends AbstractAvro {
  
  constructor(json) {
    super();
    
    this.json = json || AvroJson('User');
    this.formMapper = User[FORM_MAPPER];
    this.positionHandler = this._onPosition.bind(this);
    this.watchPosition();
  }

  watchPosition() {
    this.watchId = global.navigator.geolocation.watchPosition(this.positionHandler,
                      (err) => {
                        console.error('Error in geolocation', err);
                        API.publish(TOPICS.ERROR_MESSAGE, {error: err, additional: {
                          msg: 'Error in user geolocation',
                          args: []
                        }});
                        this.watchPosition();
                      }, U_GEOLOCATION_OPTS);
  }

  clearGeoWatch() {
    if(this.watchId) {
      global.navigator.geolocation.clearWatch(this.watchId);
    }
  }
  
  _onPosition(position) {
    console.debug('position ', position, this.lat, this.lng);
    
    let distance = 0;
    let coords = position.coords;
    if(this.lat) {
      //compare to see if the threshold is met to notify the geolocation changes
      distance = Math.sqrt(Math.pow(coords.latitude-this.lat, 2) +
        Math.pow(coords.longitude-this.lng, 2));
    }
    this.clearGeoWatch(); //just clear watch after getting it for now, annoying

    let publishGeo = false;
    let publishNavChange = false;

    if(!this.lat || distance >= GEOLOCATION_NOTIFICATION_CHANGE_THRESHOLD) {
      publishGeo = true;
      
      if(!this.lat) {
        publishNavChange = true;
      }
    }

    this.lat = coords.latitude;
    this.lng = coords.longitude;

    if(publishNavChange) {
      API.publish(TOPICS.NAVIGATION_CHANGE, Common.MAIN_NAV_PATH);
    }
    if(publishGeo) {
      API.publish(TOPICS.USER_GEO_CHANGE, {lat: coords.latitude, lng: coords.longitude});
    }
  }
  
  get id() {
    return new UserId(this.json['id']);
  }

  get lat() {
    return this.json.lat;
  }
  
  set lat(lat) {
    this.json.lat = lat;
  }

  get lng() {
    return this.json.lng;
  }
  
  set lng(lng) {
    this.json.lng = lng;
  }
  
  get email() {
    return this.getProperty('email', 'string');
  }
  
  set email(val) {
    this.json.email = {'string' : val};
  }
  
  get name() {
    return this.getProperty('name', 'string');
  }
  
  set name(val) {
    this.json.name = {'string' : val};
  }
  
  get pictureUrl() {
    let picture = this.json['picture'];
    
    return (picture && picture.url && picture.url.string) || '';
  }
  
  get password() {
    return this.getProperty('password', 'string');
  }
  
  set password(val) {
    this.json.password = {'string' : val};
  }
  
  static get [FORM_MAPPER]() {
    
    return Object.freeze([
                          {
                            field: 'email'
                          },
                          {
                            field: 'name'
                          },
                          {
                            field: 'password'
                          }
                         ]);
  }
  
  static deserialize(json) {
    console.debug('User.deserialize', json);
    
    return new User(json);
  }
  
}

export default User;
