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
    
            self.calcEndDate = function (startDate, travelTime) {
                var hours = Math.floor(parseInt(travelTime) / 60);
                var minutes = parseInt(travelTime) % 60;
                console.log("hours: " + hours + " minutes:" + minutes)
                
                var tmpDate = new Date(startDate);
                
                var hourSum = tmpDate.getHours() + hours;
                var minuteSum = tmpDate.getMinutes() + minutes;
                
                var resultISOdate = new Date(
                                    tmpDate.getFullYear(),
                                    tmpDate.getMonth(),
                                    tmpDate.getDate(),
                                    hourSum,
                                    minuteSum).toISOString();
                                            
             
                //console.log("resultISOdate:" + resultISOdate);
                return resultISOdate;
            }
            
            self.flightsInfo = AirlineFactory.getFlights();
            //console.log(self.flightsInfo);

        }]);