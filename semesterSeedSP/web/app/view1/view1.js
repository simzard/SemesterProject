'use strict';

var maxWait = 5;

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

                AirlineFactory.setFlights([]);

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

                // suggest departure date as the current date
                var d = new Date();
                self.fromDate = convertFromDateToDatePicker(d);

                self.numberOfTickets = 1;

                self.isoDate;

                // when clicked on search
                self.getInfo = function () {
                    document.getElementById("result").innerHTML = "";
                    document.getElementById("result").style.color = "cyan";
                    document.getElementById("result").innerHTML = "Searching";

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
                                    document.getElementById("result").innerHTML =
                                            "No flights found from " + from + to
                                            + " on " + self.fromDate;
                                }
                            }).error(function (error) {


                            });
                            //reset for next press
                            maxWait = 5;
                        } else {
                            document.getElementById("result").innerHTML += ".";
                        }

                    }
                    myTimer = setInterval(loadProgress, 150);

                };
            }]);