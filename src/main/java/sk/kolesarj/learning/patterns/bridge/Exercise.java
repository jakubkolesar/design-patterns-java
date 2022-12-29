package sk.kolesarj.learning.patterns.bridge;

class Device {
    private boolean enabled = false;
    private int volume = 0;

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public void setVolume(int percent) {
        this.volume = percent;
    }

    public int getVolume() {
        return this.volume;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}

class Remote {
    private Device device;

    public Remote(Device device) {
        this.device = device;
    }

    public void togglePower() {
        if (this.device.isEnabled()) {
            this.device.disable();
        } else {
            this.device.enable();
        }
    }

    public void volumeUp() {
        this.device.setVolume(Math.min(this.device.getVolume() + 10, 100));
    }

    public void volumeDown() {
        this.device.setVolume(Math.max(this.device.getVolume() - 10, 0));
    }
}

public class Exercise {
    public static void main(String[] args) {
        Device tv = new Device();
        System.out.println(tv.getVolume());
        Remote remote = new Remote(tv);

        remote.volumeUp();
        remote.volumeUp();
        remote.volumeDown();
        System.out.println(tv.getVolume());
    }
}
