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

require('./Signup.scss');

import {Grid, Row, Col, FormGroup, ControlLabel, FormControl, HelpBlock, Button, Image, Panel} from 'react-bootstrap';
import React from 'react';

import AbstractComponent from '../AbstractComponent';
import SignupAction from './actions/SignupAction';
import {TOPICS, API} from '../../common/PubSub';
import Common from '../../common/Common';

class SignupComponent extends AbstractComponent {

  constructor(props) {
    super(props);

    this.state = {
      validity: {
        email: 0, //-1 invalid, 0 initial, 1 valid
        password: 0,
        name: 0
      },
      errorMsg: ''
    };
  }
  
  componentDidMount() {
    const dropcontainer = document.getElementById('avatar');
    
    dropcontainer.addEventListener('dragenter', Common.eventCanceller, false);
    dropcontainer.addEventListener('dragover', Common.eventCanceller, false);
    dropcontainer.addEventListener('drop', this.handleDrop, false);
  }
  
  componentWillUnmount() {
    const dropcontainer = document.getElementById('avatar');
    
    dropcontainer.removeEventListener('dragenter', Common.eventCanceller);
    dropcontainer.removeEventListener('dragover', Common.eventCanceller);
    dropcontainer.removeEventListener('drop', this.handleDrop);
  }
  
  handleDrop(evt) {
    Common.eventCanceller(evt);
    
    const dt = evt.dataTransfer;
    const file = dt.files[0];

    if(!/^image\//.test(file.type)) {
      this.state.avatar.state = -1;
      return;
    }
    
    const preview = document.getElementById('avatar');
    preview.file = file;
    
    const reader = new FileReader();
    reader.onload = e => {preview.src = e.target.result};
    reader.readAsDataURL(file);
  }
  
  handleSubmit(evt) {
    console.debug('signing up');
    if(!super.handleSubmit(evt)) {
      return;
    }
    
    SignupAction.signup('signupBtn', 'signupform', 'avatar')
      .then(() => {
        //save the user and update the store
        this.state.errorMsg = '';
        
        API.publish(TOPICS.AUTH, {loggedIn: true});
      })
      .catch(message => {
        this.state.errorMsg = message;
        this.setState(this.state);
      });
  }
  
  render() {
    
    return (
        <div className='signup-component'>
          <form className='form' id='signupform' action=''>
            <Grid>
              <Row>
                <Col sm={12} md={12}>
                  <h1>Sign up</h1>
                </Col>
              </Row>
              <Row>
                <Col sm={12} md={4}>
                  <FormGroup controlId='avatar'>
                    <ControlLabel>Picture</ControlLabel>
                    <div>
                      <Image id='avatar' rounded src='/images/dropzone.png' style={{'maxHeight': '300px'}} />
                      <FormControl.Feedback />
                    </div>
                  </FormGroup>
                </Col>

                <Col sm={12}  md={8}>

                  <FormGroup controlId='name' validationState={this.getValidState('name')}>
                    <ControlLabel>Name</ControlLabel>
                    <FormControl type='text' name='name' onBlur={this.handleChange.bind(this)} />
                    <FormControl.Feedback />
                  </FormGroup>
                  
                  <FormGroup controlId='email' validationState={this.getValidState('email')}>
                    <ControlLabel>Email</ControlLabel>
                    <FormControl type='email' name='email' onBlur={this.handleChange.bind(this)}
                      placeholder='foobar@email.com' />
                    <FormControl.Feedback />
                  </FormGroup>
                  
                  <FormGroup controlId='password' validationState={this.getValidState('password')}>
                    <ControlLabel>Password</ControlLabel>
                    <FormControl type='password' name='password' onBlur={this.handleChange.bind(this)} placeholder='wsad' />
                    <FormControl.Feedback />
                    <HelpBlock>wsad best password</HelpBlock>
                  </FormGroup>

                </Col>

              </Row>
              <Row>
                <Col sm={12} md={12}>
                  {(() => {
                    
                    if(this.state.errorMsg) {
                      return <div>
                        <Panel header='Signup Error' bsStyle='danger'>
                          {this.state.errorMsg}
                        </Panel>
                      </div>;
                    }
                    
                  })()}

                  <hr />

                  <div>
                    <Button id='signupBtn' bsSize='large' bsStyle='primary' block
                      onClick={this.handleSubmit.bind(this)}>| Signup</Button>
                  </div>
                </Col>
              </Row>
            </Grid>
          </form>
        </div>
    );
  }
}

SignupComponent.displayName = 'SignupComponent';

export default SignupComponent;
