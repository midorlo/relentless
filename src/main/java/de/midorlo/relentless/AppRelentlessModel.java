package de.midorlo.relentless;

import lombok.extern.java.Log;

@Log
public class AppRelentlessModel {
    public AppRelentlessModel() {
        log.warning("This Application is not ment to be called directly.");
    }

    public static void main(String[] args) {
        new AppRelentlessModel();
    }
}
