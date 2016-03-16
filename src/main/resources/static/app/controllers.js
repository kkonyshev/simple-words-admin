(function(angular) {
    var AppController = function($scope, Word) {
        Word.query(function(response) {
            $scope.words = response ? response : [];
        });

        $scope.addWord = function(value, type) {
            new Word({
                value: value,
                type: type
            }).$save(function(word) {
                $scope.words.push(word);
            });
            $scope.newWordValue = "";
            $scope.newWordType = "";
        };

        $scope.updateWord = function(word) {
            word.$update();
        };

        $scope.deleteWord = function(word) {
            word.$remove(function() {
                $scope.words.splice($scope.words.indexOf(word), 1);
            });
        };
    };

    AppController.$inject = ['$scope', 'Word'];
    angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));