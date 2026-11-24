import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // The delay (ms) corresponds to 20 updates/second (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // TODO A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    public void startTimer() {
        this.timer.start();
    }

    /* Each step, the TimerListener moves all cars in the list and tells the
     * view to update its images. TODO Change this method to your needs.
     */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int panelWidth = frame.drawPanel.getWidth();
            int panelHeight = frame.drawPanel.getHeight();
            int imgWidth = 100; // set to your car image width
            int imgHeight = 60; // set to your car image height

            int maxX = Math.max(0, panelWidth - imgWidth);
            int maxY = Math.max(0, panelHeight - imgHeight);

            for (Car car : cars) {
                car.move();

                double x = car.getX();
                double y = car.getY();
                boolean hitWall = false;

                if (x < 0) {
                    x = 0;
                    hitWall = true;
                } else if (x > maxX) {
                    x = maxX;
                    hitWall = true;
                }

                if (y < 0) {
                    y = 0;
                    hitWall = true;
                } else if (y > maxY) {
                    y = maxY;
                    hitWall = true;
                }

                if (hitWall) {
                    car.setPosition(x, y);
                    car.brake(1.0);
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
                }

                frame.drawPanel.moveit(car, (int) Math.round(x), (int) Math.round(y));
            }

            frame.drawPanel.revalidate();
            frame.drawPanel.repaint();

        }
    }

    void gas(int amount) {
        //TODO adapt to your needs
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }

    }

    void brake(int amount) {
        //TODO adapt to your needs
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }

    }

    void turnLeft() {
        for (Car car : cars) {
            car.turnLeft() ;
        }
    }

    void turnRight() {
        for (Car car : cars) {
            car.turnRight();
        }
    }

    void startCars(){
        for (Car car : cars) {
            car.startEngine() ;
        }
    }

    void stopCars(){
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    void turboOn(){
        for (Car car : cars)
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
    }

    void turboOff(){
        for (Car car : cars)
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
    }
}
