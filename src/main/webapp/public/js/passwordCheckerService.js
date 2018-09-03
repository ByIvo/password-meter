(function () {
    'use strict';

    angular
        .module("password-strenght-checker")
        .factory("passwordCheckerService", passwordCheckerService);

    passwordCheckerService.prototype.$inject = ['$resource'];

    function passwordCheckerService($resource) {
        var resource = $resource('http://localhost:8080/measure');

        return  {
            measure: measure
        };

        function measure(passwordRequest) {
            return resource.save({}, passwordRequest);
        }
    }
})();