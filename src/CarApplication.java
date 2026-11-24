void main() {
    // Instance of this class
    CarController cc = new CarController();

    // TODO uncomment, and adapt if necessary
    Volvo240 volvo = new Volvo240();
    volvo.setPosition(0, 0);

    Saab95 saab = new Saab95();
    saab.setPosition(0, 100);

    cc.cars.add(volvo);
    cc.cars.add(saab);


    // Start a new view and send a reference to the controller
    cc.frame = new CarView("CarSim 1.0", cc);
    cc.startTimer();
}