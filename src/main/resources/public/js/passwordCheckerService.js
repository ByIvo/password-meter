(function () {
    'use strict';

    angular
        .module("password-strenght-checker")
        .factory("passwordCheckerService", passwordCheckerService);

    passwordCheckerService.prototype.$inject = ['$resource'];

    function passwordCheckerService($resource) {
        var resource = $resource(window.location.origin + window.location.pathname + 'measure');

        return  {
            measure: measure
        };

        function measure(passwordRequest) {
            return resource.save({}, passwordRequest);
        }
    }
})();