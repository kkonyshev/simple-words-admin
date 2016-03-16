(function(angular) {
    var WordFactory = function($resource) {
        return $resource('/words/:id', {
            id: '@id'
        }, {
            update: {
                method: "PUT"
            },
            remove: {
                method: "DELETE"
            }
        });
    };

    WordFactory.$inject = ['$resource'];
    angular.module("myApp.services").factory("Word", WordFactory);
}(angular));