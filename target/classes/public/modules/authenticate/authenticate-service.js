'use strict'
creativei_app.factory('AuthService', function($http){
  return {
    get: function(){
      return $http.get('../../commons/JSONs/restaurant.json')
    },
    login: function(data){
      return $http({
            method: 'POST',
            url: baseUrl + '/login',
            data: data,
            headers: {'Content-Type': 'application/json'}
        });
    }
  }
});
