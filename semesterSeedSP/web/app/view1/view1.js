'use strict';

var dots = "";




function convertDate(date) {
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
                //self.fromCity = "Copen";
                //self.toCity = "choose city";

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
                var minutes = d.getMinutes();
                if (minutes % 5 != 0)
                    minutes = 0;
                if (minutes < 10)
                    minutes = "0" + minutes;
                self.fromTime = d.getHours() + 1 + ":" + minutes;

                self.fromDate = convertDate(d);


                self.numberOfTickets = 1;


                self.getInfo = function () {

                    console.log(self.fromData.selectedOption.id);
                    console.log(self.toData.selectedOption.id);
                    console.log(self.numberOfTickets);
                    
                    function convertToIsoDate(date, time) {
    
                        return '2016-01-04T23:00:00.000Z';
                    }
                    
                    
                    var isoDate = convertToIsoDate(self.fromDate, self.fromTime);
                    
                    //construct isoDate
                    isoDate+= d.getFullYear() + "-" + d.getMonth()

                    AirlineFactory.makeRESTcall(
                            self.fromData.selectedOption.id,
                            self.toData.selectedOption.id,
                            isoDate,
                            self.numberOfTickets
                            );
                    
                    self.flightsInfo = AirlineFactory.getFlights();

                    var myTimer;

                    function loadProgress() {

                        if (self.flightsInfo.length > 0) {
                            clearInterval(myTimer);
                            window.location = '#/view2';
                        } else {
                            dots += ".";
                            document.getElementById("result").innerHTML = dots;
                        }
                    }

                    myTimer = setInterval(loadProgress, 500);






                    console.log(self.flightsInfo);
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