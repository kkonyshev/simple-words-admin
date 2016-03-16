(function(angular) {
    var AppController = function($scope, $http, Word) {
        $scope.newWord = { "value" : "", "type" : ""};

        Word.query(function(response) {
            $scope.words = response ? response : [];
        });

        $scope.wordTypeList = [];
        $http.get('/word/type').success(function(data) {
            $scope.wordTypeList = data;
        });

        $scope.addWord = function(newWord) {
            newWord.value.split(/\s+/).forEach(function(v) {
                new Word({
                    value: v,
                    type: newWord.type
                }).$save(function (word) {
                    $scope.words.push(word);
                });
            });

            $scope.newWord.value = "";
        };

        $scope.updateWord = function(word) {
            word.$update();
        };

        $scope.deleteWord = function(word) {
            word.$remove(function() {
                $scope.words.splice($scope.words.indexOf(word), 1);
            });
        };

        $scope.newWord.value = "";
        $scope.newWord.type = "NOUN";
    };

    AppController.$inject = ['$scope', '$http', 'Word'];
    angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));