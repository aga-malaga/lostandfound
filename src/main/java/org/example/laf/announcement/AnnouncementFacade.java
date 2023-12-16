package org.example.laf.announcement;

import java.util.List;

public interface AnnouncementFacade {
    void accept();
    void delete();
    void publish();
    void update();
    List findForPortal();
}