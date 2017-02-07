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

from django.shortcuts import render
from django.http import JsonResponse, HttpResponse, HttpResponseBadRequest
from django import forms
from shared.kafka import Publisher

import logging
import datetime

logger = logging.getLogger(__name__)

class LocationForm(forms.Form):
  name = forms.CharField()
  description = forms.CharField(max_length=100)
  lat = forms.DecimalField(max_digits=9, decimal_places=6)
  lng = forms.DecimalField(max_digits=9, decimal_places=6)
  userId = forms.IntegerField()
  tags = django.forms.CharField()

  def clean_tags(self):
    return self.cleaned_data['tags'].strip().split()

def addLocation(request):
  logger.debug('addLocation')
  form = LocationForm(request.POST)
  if(form.is_valid()):
    cleaned = form.cleaned_data


    return JsonResponse({
      'code': 'SUCCESS',
      'data': [],
      'message': ''
    });
  else:
    return HttpResponseBadRequest()

def queryLocation(request, userId, lat, lng):
  logger.debug('queryLocation ' + userId + ' - ' + lat + '/' + lng)
  return JsonResponse({
      'code': 'SUCCESS',
      'data': [
        {
          'name': 'TEST',
          'description': 'Test',
          'lat': 55.2344,
          'lng': 56.4900,
          'user_id': 1,
          'creation_date': datetime.datetime.now()
        },
        {
          'name': 'ANOTHER',
          'description': 'Another',
          'lat': 79.2534,
          'lng': 32.9720,
          'user_id': 2,
          'creation_date': datetime.datetime.now()
        }
      ]
    })