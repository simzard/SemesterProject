'use strict';



angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View1Ctrl', ["InfoFactory", "InfoService", function (InfoFactory, InfoService, $http) {

                var self = this;
                self.fromCity = "";
                self.toCity = "";
                self.fromDate = "";
                self.toDate = "";

                self.numberOfTickets = 0;

                this.msgFromFactory = InfoFactory.getInfo();
                this.msgFromService = InfoService.getInfo();

                self.buttonClicked = false;

                self.getInfo = function () {

                    self.buttonClicked = true;


//                    $http.get("flightInfo/" + self.from + "/" + self.date + "/" + self.numberOfTickets).succes(function (data) {
//                        self.flightsInfo = data;
//                    });

                    self.dummyData = [
                        {
                            "airline": "AngularJS Airline",
                            "flightId": "COL3256",
                            "numberOfSeats": 3,
                            "date": "2016-01-04T10:00:00.000Z",
                            "priceTotal": 195.0,
                            "travelTime": 90,
                            "origin": "CPH",
                            "destination": "STN"
                        },
                        {
                            "airline": "Martins Airline",
                            "flightId": "HUH3256",
                            "numberOfSeats": 25,
                            "date": "2016-01-04T10:00:00.000Z",
                            "priceTotal": 40.0,
                            "travelTime": 15,
                            "origin": "CPH",
                            "destination": "STN"
                        },
                        {
                            "airline": "Simons Airline",
                            "flightId": "SI16",
                            "numberOfSeats": 250,
                            "date": "2016-01-04T10:00:00.000Z",
                            "priceTotal": 12,
                            "travelTime": 20,
                            "origin": "CPH",
                            "destination": "STN"
                        }
                    ]
                };
            }]);