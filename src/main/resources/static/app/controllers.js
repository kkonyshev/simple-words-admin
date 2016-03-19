(function(angular) {
    var WordController = function($scope, $http, Word) {
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
                    $scope.words.sort(function(a,b) {return (a.value > b.value) ? 1 : ((b.value > a.value) ? -1 : 0);} );
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

    WordController.$inject = ['$scope', '$http', 'Word'];
    angular.module("myApp.controllers").controller("WordController", WordController);


    var CollocationController = function($scope, $http, Collocation) {
        $scope.newCollocation = { "value" : "", "type" : "", "words" : []};

        Collocation.query(function(response) {
            $scope.collocations = response ? response : [];
        });

        $scope.collocationTypeList = [];
        $http.get('/collocation/type').success(function(data) {
            $scope.collocationTypeList = data;
        });

        $scope.addCollocation = function(newCollocation) {
            new Collocation({
                value: newCollocation.value,
                type: newCollocation.type,
                words: []
            }).$save(function (collocation) {
                $scope.collocations.push(collocation);
                $scope.collocations.sort(function(a,b) {return (a.value > b.value) ? 1 : ((b.value > a.value) ? -1 : 0);} );
            });
            $scope.newCollocation.value = "";
            $scope.newCollocation.words = [];
        };

        $scope.updateCollocation = function(collocation) {
            collocation.$update();
        };

        $scope.deleteCollocation = function(collocation) {
            collocation.$remove(function() {
                $scope.collocations.splice($scope.collocations.indexOf(collocation), 1);
            });
        };

        $scope.newCollocation.value = "";
        $scope.newCollocation.type = "SENTENCE";
        $scope.addCollocation.words = [];
    };

    CollocationController.$inject = ['$scope', '$http', 'Collocation'];
    angular.module("myApp.controllers").controller("CollocationController", CollocationController);
}(angular));