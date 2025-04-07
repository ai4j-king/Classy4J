package com.classy4j.helper;

import org.springframework.stereotype.Component;

@Component
public class PositionHelper {
    public static final int DEFAULT_POSITION = 0;
    
    public int calculateNextPosition(int currentMaxPosition) {
        return currentMaxPosition + 1;
    }
    
    public int getDefaultPosition() {
        return DEFAULT_POSITION;
    }
    
    public boolean isValidPosition(int position) {
        return position >= 0;
    }
    
    public int normalizePosition(int position) {
        return Math.max(position, DEFAULT_POSITION);
    }
}