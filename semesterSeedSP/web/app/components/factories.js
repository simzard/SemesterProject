'use strict';

/* Place your global Factory-service in this file */

angular.module('myApp.factories', []).
        factory('AirlineFactory', ["$http", function ($http) {
                

                var urlBase = '/api/flightinfo/';
                var AirlineFactory = {};
                
                AirlineFactory.flights = [];


                AirlineFactory.setFlights = function(f) {
                    console.log("f:" + f)
                    angular.copy(f, AirlineFactory.flights);
                }

                AirlineFactory.getFlights = function () {
                    return AirlineFactory.flights;
                }

                var makeRESTcallFromTo = function (from, to, date, persons) {
                    console.log(urlBase + from + '/' + to + '/' + date + '/' + persons);
                    return ($http.get(urlBase + from + '/' + to + '/' + date + '/' + persons));
                };

                var makeRESTcallFrom = function (from, date, persons) {
                    console.log(urlBase + from + '/' + date + '/' + persons);
                    return ($http.get(urlBase + from + '/' + date + '/' + persons));
                };

                AirlineFactory.makeRESTcall = function (from, to, date, persons) {
                    if (to != "choose city") {
                        return makeRESTcallFromTo(from, to, date, persons);
                    } else {
                        return makeRESTcallFrom(from, date, persons);
                    }
                }
                return AirlineFactory;
            }])
