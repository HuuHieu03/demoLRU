package org.example.demotuan8_lru.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.example.demotuan8_lru.logic.LRUAlgorithm;

import java.util.*;

public class LRUController {

    @FXML private TextField frameField;
    @FXML private TextField referenceField;
    @FXML private HBox simulationArea;
    @FXML private Label statusLabel;

    private int frameCount;
    private List<Integer> referenceString = new ArrayList<>();
    private LRUAlgorithm lru;

    public void handleStartSimulation() {
        try {
            frameCount = Integer.parseInt(frameField.getText().trim());
            referenceString = parseReferenceString(referenceField.getText());

            lru = new LRUAlgorithm(frameCount, referenceString);
            simulationArea.getChildren().clear();
            statusLabel.setText("Mô phỏng đã khởi động!");
            drawFrameState(lru.getFrames(), false);
        } catch (Exception e) {
            statusLabel.setText("Lỗi: Kiểm tra lại dữ liệu đầu vào.");
        }
    }

    public void handleNextStep() {
        if (lru == null || lru.isFinished()) {
            statusLabel.setText("Đã hoàn thành chuỗi tham chiếu.");
            return;
        }

        int currentPage = lru.getCurrentPage();
        boolean isHit = lru.nextStep();
        drawFrameState(lru.getFrames(), isHit);

        statusLabel.setText("Đang xử lý trang: " + currentPage + (isHit ? " (HIT)" : " (FAULT)"));
    }

    private void drawFrameState(List<Integer> frames, boolean isHit) {
        VBox frameColumn = new VBox(5);
        frameColumn.setStyle("-fx-border-color: black; -fx-padding: 5;");
        frameColumn.getChildren().add(new Label("Bước " + lru.getCurrentStep()));
        if (!isHit) {
            for (int i = 0; i < frameCount; i++) {
                Label cell = new Label();
                cell.setMinSize(50, 40);
                cell.setFont(Font.font(16));
                cell.setStyle("-fx-border-color: gray; -fx-alignment: center;");
                if (i < frames.size()) {
                    cell.setText(String.valueOf(frames.get(i)));
                } else {
                    cell.setText("-");
                }
                frameColumn.getChildren().add(cell);
            }
        }

        simulationArea.getChildren().add(frameColumn);
    }

    private List<Integer> parseReferenceString(String text) {
        String[] parts = text.trim().split(",");
        List<Integer> result = new ArrayList<>();
        for (String part : parts) {
            result.add(Integer.parseInt(part.trim()));
        }
        return result;
    }
}
