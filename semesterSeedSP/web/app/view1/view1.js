'use strict';

angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View1Ctrl', ["InfoFactory", "InfoService", function (InfoFactory, InfoService) {
                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();


                this.buttonClicked = false;
                this.getResult = function () {

                    var object = {
                        "airline": "AngularJS Airline",
                        "flights": [
                            {
                                "flightId": "COL3256",
                                "numberOfSeats": 3,
                                "date": "2016-01-04T10:00:00.000Z",
                                "priceTotal": 195.0,
                                "travelTime": 90,
                                "origin": "CPH",
                                "destination": "STN"
                            }]
                    };
                    return object;
                };
            }]);