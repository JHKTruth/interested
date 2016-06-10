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

import {Component} from 'react';

class AbstractComponent extends Component {
  
  constructor(props) {
    super(props);
  }
  
  handleChange(evt) {
    console.info('handleChange ', evt);
    
    let target = evt.target;
    let eleId = target.id;
    let oldState = this.state[eleId].state;
    
    this.state[eleId].state = target.value.trim().length > 0 ? 1 : -1;
    if(oldState !== this.state[eleId].state) {
      this.setState(this.state);
    }
  }
  
  getValidState(elementId) {
    let state;
    
    switch(this.state[elementId].state) {
    case 0: break;
    case 1: state = 'success'; break;
    case -1: state = 'error'; break;
    default: state = 'WRONG HERE!!!!!!!'; break;
    }
    
    console.info('elementId', elementId, state);
    return state;
  }
  
  render() {
    throw new Error('AbstractComponent should not be used standalone');
  }
}

AbstractComponent.displayName = 'AbstractComponent';

export default AbstractComponent;