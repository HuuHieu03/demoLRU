package org.example.demotuan8_lru.logic;

import java.util.*;

public class LRUAlgorithm {
    private final int frameCount;
    private final List<Integer> referenceString;

    private final List<Integer> frames = new ArrayList<>();
    private final LinkedHashMap<Integer, Integer> lruMap = new LinkedHashMap<>();

    private int currentStep = 0;

    public LRUAlgorithm(int frameCount, List<Integer> referenceString) {
        this.frameCount = frameCount;
        this.referenceString = referenceString;
    }

    public boolean isFinished() {
        return currentStep >= referenceString.size();
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public int getCurrentPage() {
        return referenceString.get(currentStep);
    }

    public List<Integer> getFrames() {
        return new ArrayList<>(frames);
    }

    public boolean nextStep() {
        if (isFinished()) return false;

        int currentPage = referenceString.get(currentStep);
        boolean isHit = frames.contains(currentPage);

        if (isHit) {
            lruMap.remove(currentPage);
        } else {
            if (frames.size() >= frameCount) {
                int lruPage = lruMap.keySet().iterator().next();
                frames.remove((Integer) lruPage);
                lruMap.remove(lruPage);
            }
            frames.add(currentPage);
        }

        lruMap.put(currentPage, currentStep);
        currentStep++;

        return isHit;
    }
}
