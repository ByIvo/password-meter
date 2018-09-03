(function () {
    'use strict';

    angular
        .module("password-strenght-checker")
        .controller("passwordCheckerController", passwordCheckerController);

    passwordCheckerController.prototype.$inject = [
        'passwordCheckerService',
        '$scope',
    ];

    function passwordCheckerController(
        passwordCheckerService,
        $scope
    ) {
        $scope.password = '';
        $scope.measure = measurePassword;

        $scope.passwordResult = {
            score: 0,
            complexity: 'Too short'
        };

        function measurePassword() {
               var passwordRequest = {
                rawPassword: $scope.password
            };

            passwordCheckerService.measure(passwordRequest).$promise.then(function(passwordResult) {
                $scope.passwordResult = passwordResult;
            })
            .catch(function(error) {
                console.log(error);
            });
        }
    }
})();