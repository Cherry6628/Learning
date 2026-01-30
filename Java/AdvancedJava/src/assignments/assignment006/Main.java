package assignments.assignment006;

class SmartHome {
    private String masterKey = "1234";
    private Device currentDevice;

    class Device {
        String deviceName;
        boolean isOn;
        Device(String name,boolean isOn){this.deviceName=name;this.isOn=isOn;}
        Device(String deviceName){this(deviceName, false);}
        void turnOn(){
            isOn = true; System.out.println(deviceName + " is now ON");
        }
        void turnOff(){
            isOn = false; System.out.println(deviceName + " is now OFF");
        }

        void status(){System.out.println(deviceName + " status: " + (isOn ? "ON" : "OFF"));}
    }
    interface Operations{void execute();}
    static class Settings{
        static int maxDevicesAllowed = 10;
        static String systemVersion = "1.0";
        void showSettings() {
            System.out.println("Max Devices Allowed: " + maxDevicesAllowed);
            System.out.println("System Version: " + systemVersion);
        }
    }
    
    boolean authenticate(String enteredKey){
        class AccessValidator{
            boolean validate(){
                return enteredKey.equals(masterKey);
            }
        }
        AccessValidator validator = new AccessValidator();
        return validator.validate();
    }

    void switchDevice(Device device){
        currentDevice = device;
        System.out.println("Switched to device: " + device.deviceName);
    }

    Device getCurrentDevice(){return currentDevice;}

    void performTemporaryAction(){
        Operations tempAction = new Operations(){
            public void execute(){
                System.out.println("Performing a temporary action inside SmartHome...");
            }
        };
        tempAction.execute();
    }
}

public class Main {
    public static void main(String[] args){
        SmartHome home = new SmartHome();
        if (home.authenticate("1234")){
            System.out.println("Authentication Successful");
        } else{
            System.out.println("Access Denied");
            return;
        }

        SmartHome.Device fan = home.new Device("Fan");
        SmartHome.Device light = home.new Device("Light");

        home.switchDevice(fan);
        fan.status();
        fan.turnOn();
        fan.turnOff();

        home.switchDevice(light);
        light.status();
        light.turnOn();
        light.turnOff();

        home.performTemporaryAction();

        SmartHome.Settings settings = new SmartHome.Settings();
        settings.showSettings();
    }
}
