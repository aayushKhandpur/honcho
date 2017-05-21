//validate credetials
//fetch user role
//fetch restaurant info
//save restaurant info, user role, login info in rootscope
'use strict';
creativei_app.controller('AuthController', function($scope, $rootScope, AuthService,$state, $localStorage){
  console.log("Inside auth controller");
  $scope.$storage = $localStorage;
  $scope.authenticate = function(){
    if(!$scope.user || !$scope.user.userId || !$scope.user.password){
      console.log("Invalid credentials.");
    }else{
      var data = {
        userId : $scope.user.userId,
        password : $scope.user.password
      };
      AuthService.login(data).then(function(response){
        if(response && response.data){
          if(response.data.status ==="ERROR"){
            console.log(response.data.exception);
            alert(response.data.exception.errorCode + ": " + "response.data.exception.message");
            $rootScope.isAuthenticated = false;
            $scope.$storage.isAuthenticated = false;
          }else{
            $scope.restaurant= response.data.data;
            $rootScope.isAuthenticated = true;
            $scope.$storage.isAuthenticated = true;
            $scope.$storage.restaurant = response.data.data;
            $rootScope.restaurant=response.data.data;
            $state.go('services');
          }
        }else{
          console.log("No response");
        }
      });
    }

  }
  $scope.logout = function(){
    delete $rootScope.isAuthenticated
    delete $scope.$storage.isAuthenticated
    $state.go('login');
  }

});
