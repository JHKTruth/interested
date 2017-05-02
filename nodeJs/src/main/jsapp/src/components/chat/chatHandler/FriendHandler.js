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

import avrojson from '../../../avro/avrojson';
import UserId from '../../../avro/UserId';
import Storage from '../../../common/Storage';
import {TOPICS, API} from '../../../common/PubSub';

import {CHAT_TYPE} from '../ChatAreaHelper';

import FriendRequestAction from '../../common/actions/friendship/FriendRequestAction';

export default function (split, user) {
  if(split[0] === '/friendRequest' && split.length === 2) {

    const friendId = UserId(avrojson['UserId']);
    friendId.id = split[1];

    FriendRequestAction.friendRequest(user.id, friendId)
      .then((data) => {
        const cMessage = `Friend Request from: ${user.name}. Type /friendJoin ${user.name}`;

        this.ws.send('/pulsing/privateChat/' + friendId.id, {},
              JSON.stringify({message: cMessage, userId: user.id.id, type: CHAT_TYPE.FRIEND_INVITE,
                              data: {invitationId: data.invitationId}, name: user.name}));
      });
  }
};
