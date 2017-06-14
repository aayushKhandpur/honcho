creativei_app.controller('kotController', function ($scope) {
    console.log("Inside kot controller.");
    
//    $scope.selectedPreset = {layout: '4x3',column: '3',height: '150px'};
    
    $scope.kotLayout = {        
        availablePreset : [
            {layout: '6x4',column: '2',height: '130px'},
            {layout: '4x3',column: '3',height: '190px'},
            {layout: '3x3',column: '4',height: '190px'}
        ],
        selectedPreset : '3'
    };
    $scope.getHeight = function(){
        if($scope.kotLayout.selectedPreset == '2'){
            return '130px;';
        }
        else{
            return '190px;';
        }
    };
//    $scope.getSelectedPreset = function(preset){
//        $scope.selectedPreset = preset;
//        console.log($scope.selectedPreset);
//    };
});
