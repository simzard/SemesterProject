'use strict';

var dots = "";
var maxWait = 3;

function convertFromDatePickerToISODate(datepickerDate) {
    var tokens = datepickerDate.split(" ");
    var day = parseInt(tokens[0]);
    var month;
    switch (tokens[1]) {
        case "January":
            month = 0;
            break;
        case "February":
            month = 1;
            break;
        case "March":
            month = 2;
            break;
        case "April":
            month = 3;
            break;
        case "May":
            month = 4;
            break;
        case "June":
            month = 5;
            break;
        case "July":
            month = 6;
            break;
        case "August":
            month = 7;
            break;
        case "September":
            month = 8;
            break;
        case "October":
            month = 9;
            break;
        case "November":
            month = 10;
            break;
        case "December":
            month = 11;
            break;

    }
    var year = tokens[2];
    var theDate = new Date(year, month, day, 1).toISOString();
    console.log("theDate: " + theDate);
    return theDate;
}

function convertFromDateToDatePicker(date) {
    // format: "day month year
    // input: "Tue Dec 15 2015"
    // desired: "15 December 2015"
    var month;
    switch (date.getMonth()) {
        case 0:
            month = "January";
            break;
        case 1:
            month = "February";
            break;
        case 2:
            month = "March";
            break;
        case 3:
            month = "April";
            break;
        case 4:
            month = "May";
            break;
        case 5:
            month = "June";
            break;
        case 6:
            month = "July";
            break;
        case 7:
            month = "August";
            break;
        case 8:
            month = "September";
            break;
        case 9:
            month = "October";
            break;
        case 10:
            month = "November";
            break;
        case 11:
            month = "December";
            break;
    }

    return date.getDate() + " " + month + " " + date.getFullYear();
}
;

angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View1Ctrl', ["AirlineFactory", function (AirlineFactory) {

                var self = this;



                // options in select boxes
                self.fromData = {
                    availableOptions: [
                        {id: 'CPH', name: 'Copenhagen (CPH)'},
                        {id: 'STN', name: 'London (STN)'},
                        {id: 'SXF', name: 'Berlin (SXF)'},
                        {id: 'PAR', name: 'Paris (PAR)'},
                        {id: 'JRA', name: 'New York (JRA)'},
                        {id: 'NRT', name: 'Tokyo (NRT)'},
                        {id: 'NAY', name: 'Beijing (NAY)'},
                        {id: 'MOW', name: 'Moscow (MOW)'},
                        {id: 'MAD', name: 'Madrid (Mad)'},
                        {id: 'BMA', name: 'Stockholm (BMA)'},
                    ],
                    selectedOption: {id: 'CPH', name: 'Copenhagen (CPH)'} //This sets the default value of the select in the ui
                };

                self.toData = {
                    availableOptions: [
                        {id: 'CPH', name: 'Copenhagen (CPH)'},
                        {id: 'STN', name: 'London (STN)'},
                        {id: 'SXF', name: 'Berlin (SXF)'},
                        {id: 'PAR', name: 'Paris (PAR)'},
                        {id: 'JRA', name: 'New York (JRA)'},
                        {id: 'NRT', name: 'Tokyo (NRT)'},
                        {id: 'NAY', name: 'Beijing (NAY)'},
                        {id: 'MOW', name: 'Moscow (MOW)'},
                        {id: 'MAD', name: 'Madrid (Mad)'},
                        {id: 'BMA', name: 'Stockholm (BMA)'},
                    ],
                    selectedOption: {id: 'choose city', name: 'choose city'} //This sets the default value of the select in the ui
                };

                // always suggest the next time from the current time + 1 hour
                var d = new Date();

                self.fromDate = convertFromDateToDatePicker(d);

                self.numberOfTickets = 1;

                self.isoDate;

                self.getInfo = function () {
                    self.result = "";
                    document.getElementById("result").style.color = "cyan";
                    self.result = "Searching...";

                    var myTimer;

                    function loadProgress() {
                        maxWait--;
                        if (maxWait == 0) {
                            clearInterval(myTimer);
                            self.isoDate = convertFromDatePickerToISODate(self.fromDate);

                            console.log(self.fromData.selectedOption.id);
                            console.log(self.toData.selectedOption.id);
                            console.log(self.numberOfTickets);

                            AirlineFactory.makeRESTcall(
                                    self.fromData.selectedOption.id,
                                    self.toData.selectedOption.id,
                                    self.isoDate,
                                    self.numberOfTickets
                                    ).success(function (data) {
                                if (data.constructor === Array) {
                                    AirlineFactory.setFlights(data);
                                }

                                if (AirlineFactory.getFlights().length > 0)
                                    window.location = '#/view2';
                                else {
                                    var from = self.fromData.selectedOption.name;
                                    var to;
                                    if (self.toData.selectedOption.name == 'choose city') {
                                        to = " ";
                                    } else {
                                        to = " to " + self.toData.selectedOption.name;
                                    }
                                    document.getElementById("result").style.color = "red";
                                    self.result = "No flights found from " + from + to
                                            + " on the chosen date ";
                                }
                            }).error(function (error) {
                                self.result = "Failure!";

                            });
                            //reset for next press
                            maxWait = 3;
                            dots = "";
                            self.result = "";
                        } 
                        
                    }
                    myTimer = setInterval(loadProgress, 200);


                    //construct isoDate




//
//                    
//
//                    






                    //$http.get("flightInfo/" + self.from + "/" + self.date + "/" + self.numberOfTickets).succes(function (data) {
//                    $http({
//                        method: 'GET',
//                        url: 'api/flightinfo/CPH/2016-01-04T23:00:00.000Z/3'
//                    }).then(function successCallback(res) {
//                        self.flightsInfo = res.data;
//                    }, function errorCallback(res) {
//                        $scope.error = res.status + ": " + res.data.statusText;
//                    });
//                    $http.get("http://localhost:8084/semesterSeedSP/api/flightinfo/CPH/2016-01-04T23:00:00.000Z/3").succes(function (data) {
//                        self.flightsInfo = data;
//                    });

//                    self.dummyData = [
//                        {
//                            "airline": "AngularJS Airline",
//                            "flightId": "COL3256",
//                            "numberOfSeats": 3,
//                            "date": "2016-01-04T10:00:00.000Z",
//                            "priceTotal": 195.0,
//                            "travelTime": 90,
//                            "origin": "CPH",
//                            "destination": "STN"
//                        },
//                        {
//                            "airline": "Martins Airline",
//                            "flightId": "HUH3256",
//                            "numberOfSeats": 25,
//                            "date": "2016-01-04T10:00:00.000Z",
//                            "priceTotal": 40.0,
//                            "travelTime": 15,
//                            "origin": "CPH",
//                            "destination": "STN"
//                        },
//                        {
//                            "airline": "Simons Airline",
//                            "flightId": "SI16",
//                            "numberOfSeats": 250,
//                            "date": "2016-01-04T10:00:00.000Z",
//                            "priceTotal": 12,
//                            "travelTime": 20,
//                            "origin": "CPH",
//                            "destination": "STN"
//                        }
//                    ]
                };
            }]);