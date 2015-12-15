'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'app/view2/view2.html',
                    controller: 'View2Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View2Ctrl', ["AirlineFactory", function (AirlineFactory) {
            var self = this;
            
            self.flightsInfo = AirlineFactory.getFlights();
            console.log(self.flightsInfo);

        }]);