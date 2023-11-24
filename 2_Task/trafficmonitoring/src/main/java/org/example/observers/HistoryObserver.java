package org.example.observers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HistoryObserver implements IObserver {

    private static final Logger logger = LogManager.getLogger(HistoryObserver.class);

    @Override
    public void update(String notifyAbout) {
        //logger.info(notifyAbout);

    }
}
