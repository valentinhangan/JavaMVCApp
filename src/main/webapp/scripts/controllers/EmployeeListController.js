hrApp.controller('EmployeeListController', ['$scope', '$http', '$location', function($scope, $http, $location) {
    $scope.employees = [];
    $http({url: 'http://localhost:8282/datamodel/employees/findAll', method: 'GET'}).
        success(function(data, status, headers, config) {
            $scope.employees = data;
        });
    $scope.viewEmployee = function(employeeId) {
        $location.url('/employeeview/'+employeeId);
    };
    $scope.editEmployee = function(employeeId) {
        $location.url('/employeeedit/'+employeeId);
    };
    $scope.deleteEmployee = function(employeeId) {
        $location.url('/employeedelete/'+employeeId);
    };
}]);