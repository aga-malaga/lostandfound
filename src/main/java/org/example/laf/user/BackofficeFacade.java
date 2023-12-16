package org.example.laf.user;

import java.util.UUID;

public interface BackofficeFacade {
    void activateUser(UUID userUuid);
    void acceptAnnouncement();
    void deleteAnnouncement();
}