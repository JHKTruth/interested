"""
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.

@author Ji Kim
"""

import json
import logging
import uuid

from django.http import JsonResponse, HttpResponseBadRequest

from shared.kafka import Publisher
from shared.redis import Redis

FRIEND_TOPIC = 'FRIEND'
logger = logging.getLogger(__name__)
redis = Redis()
publisher = Publisher()

def friendInvitationId(friendId):
    return '_'.join(['FRIEND_REQUEST_INVITE', friendId, str(uuid.uuid4())])

#{"expiration":1493846911462,"invitationId":"CHAT_LOBBY_INVITE_2_40372843577401","fromUserId":1,"invitationType":"CHAT_LOBBY_INVITE"}

def friendJoin(request):
    """
    1) check the invitationId did not expire
    2) send a message to kafka of the friend
    3) on the client side notify the friendId a friend has joined
    """
    parameters = [field for field in ['userId', 'invitationId'] if not field in request.POST]
    
    if len(parameters) > 0:
        logger.debug('friendJoin lacking parameters - %s', parameters)
        return HttpResponseBadRequest()
    
    userId = request.POST['userId']
    invitationId = request.POST['invitationId']
    
    logger.debug('friendJoin %s - %s ', invitationId, userId)
    
    invitation = redis.removeInvitation(userId, invitationId)
    
    logger.debug('friendJoin.invitation %d ', len(invitation))
    # publisher.publish(FRIEND_TOPIC, json.dumps())
    
    if len(invitation) == 1:
        return JsonResponse({
            'code': 'SUCCESS',
            'data': [],
            'message': ''
        })
    else:
        return JsonResponse({
            'code': 'FAILURE',
            'data': [],
            'message': ''
        })
    
def friendRequest(request):
    """ 
    technically does not have to perform the round trip as can pass off
    to websocket on the spring side; however to play around with redis initially
    with django am sending the request here with websocket controller only sending
    out the system alert
    """
    parameters = [field for field in ['userId', 'friendId'] if not field in request.POST]
    
    if len(parameters) > 0:
        logger.debug('friendRequest lacking parameters - %s', parameters)
        return HttpResponseBadRequest()
    
    userId = request.POST['userId']
    friendId = request.POST['friendId']
    
    logger.debug('friendRequest %s - %s ', userId, friendId)
    
    # TODO need to check if they are already friends or not
    # user = User.objects.get_user(id=userId)
    # friend = User.objects.get_user(id=friendId)
    
    invitation_id = friendInvitationId(friendId)
    redis.storeInvitation(friendId, userId, invitation_id, 'FRIEND_REQUEST_INVITE')
    
    invitations = redis.client.smembers('INVITATIONS_'+friendId) 
    
    logger.debug('Invitations %s', invitations)
    
    return JsonResponse({
        'code': 'SUCCESS',
        'data': {'invitationId': invitation_id},
        'message': ''
    })
    
