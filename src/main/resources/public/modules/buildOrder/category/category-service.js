creativei_app.factory('CategoryService', function($http){
  return {
    getCategories: function(){
      return $http({
            method: 'GET',
            url: baseUrl + '/categories',
            headers: {'Content-Type': 'application/json'}
        });
    }
  }
});
