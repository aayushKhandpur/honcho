creativei_app.factory('RestaurantService', function($http){
  return {
    getTables: function(){
      return $http({
            method: 'GET',
            url: baseUrl + '/restaurant/tables',
            headers: {'Content-Type': 'application/json'}
        });
    }
  }
});
