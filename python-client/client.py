# Requests is an external library and needs to be installed first
# `sudo pip install requests`
# 
# To run `python client.py`
import requests
import json

resp = requests.get('http://localhost:9086/api-jva-openapi-server/v1/pets/2')
if resp.status_code != 200:
    raise ApiError('GET /tasks/ {}'.format(resp.status_code))
else:
    pet = resp.json()
    print(pet)