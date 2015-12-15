'use strict';

/* Place your global Factory-service in this file */

angular.module('myApp.factories', []).
        factory('AirlineFactory', ["$http", function ($http) {
                var flights = [];

                var getFlights = function () {
                    return flights;
                }
                
                var makeRESTcallFromTo = function (from, to, date, persons) {
                    $http({
                        method: 'GET',
                        url: 'api/flightinfo/' + from + '/' + to + '/' + date + '/' + persons
                    }).then(function successCallback(res) {
                        flights = res.data;
                    }, function errorCallback(res) {
                        fligths = [];
                    });
                };
                
                var makeRESTcallFrom = function (from, date, persons) {
                    var urlString = 'api/flightinfo/' + from + '/' + date + '/' + persons;
                    console.log(urlString);
                    $http({
                        method: 'GET',
                        url: urlString
                    }).then(function successCallback(res) {
                        flights = res.data;
                    }, function errorCallback(res) {
                        fligths = [];
                    });
                };
                
                var makeRESTcall = function (from, to, date, persons) {
                    if (to != "choose city") {
                       makeRESTcallFromTo(from, to, date, persons);
                    } else {
                        makeRESTcallFrom (from, date, persons);
                    }
                    
                             

                }
                return {
                    getFlights: getFlights,
                    makeRESTcall: makeRESTcall
                };
            }])
