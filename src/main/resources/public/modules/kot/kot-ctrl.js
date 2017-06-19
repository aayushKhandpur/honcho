creativei_app.controller('KOTController', function ($scope) {
    console.log("Inside kot controller.");
        
    $scope.kotLayout = {        
        availablePreset : [
            {layout: '6x4',column: '2',height: '130px'},
            {layout: '4x3',column: '3',height: '190px'},
            {layout: '3x3',column: '4',height: '190px'}
        ],
        selectedPreset : '3'                                //sets the defaul layout to 3 columns
    };
    $scope.getHeight = function(){                          //returns the height for the order card
        if($scope.kotLayout.selectedPreset == '2'){
            return '130px;';                                //Optimal height for 6x4 layout on a display of resolution 1366x768
        }
        else{
            return '190px;';                                //Optimal height for 4x3 & 3x3 layout on a display of resolution 1366x768
        }
    };
});
