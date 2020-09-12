package com.example.caching_test.Config;


import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class CacheEventLogger implements CacheEventListener{
    private static Logger logger = LoggerFactory.getLogger(CacheEventLogger.class);

    @Override
    public void notifyElementRemoved(Ehcache ehcache, Element element) throws CacheException {
        logger.info("Element Removed: "+ element.toString(), element.toString(), element, ehcache);
    }

    @Override
    public void notifyElementPut(Ehcache ehcache, Element element) throws CacheException {
        logger.info("Element Put: "+ element.toString(), element.toString(), element, ehcache);
    }

    @Override
    public void notifyElementUpdated(Ehcache ehcache, Element element) throws CacheException {
        logger.info("Element Updated: "+ element.toString() , element.toString(), element, ehcache);
    }

    @Override
    public void notifyElementExpired(Ehcache ehcache, Element element) {
        logger.info("Element Expired: "+ element.toString(), element.toString(), element, ehcache);
    }

    @Override
    public void notifyElementEvicted(Ehcache ehcache, Element element) {
        logger.info("Element Evicted: "+ element.toString(), element.toString(), element, ehcache);
    }

    @Override
    public void notifyRemoveAll(Ehcache ehcache) {
        logger.info("Cache Clear", ehcache);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public void dispose() {

    }
}
