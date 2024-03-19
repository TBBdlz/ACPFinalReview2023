package yingyongwatthanakit.metee.demo.gui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class SimpleWindowTest {

    SimpleWindow window;

    @BeforeEach
    void setUp() {
        // Set up a SimpleWindow instance before each test
        window = new SimpleWindow("Test Window");
    }

    @Test
    void initializeComponents() {
        window.initializeComponents();
        // Test that components are not null after initialization
        assertNotNull(window.confirmButton, "confirmButton should not be null after initialization");
        assertNotNull(window.buttonPanel, "buttonPanel should not be null after initialization");
        assertNotNull(window.mainPanel, "mainPanel should not be null after initialization");
    }

    @Test
    void addComponents() {
        // Initialize components before trying to add them
        window.initializeComponents();
        window.addComponents();
        // Since addComponents mainly adds components to panels, check if they are correctly parented
        assertEquals(window.buttonPanel, window.confirmButton.getParent(), "confirmButton should be added to buttonPanel");
        assertTrue(window.mainPanel.isAncestorOf(window.buttonPanel), "buttonPanel should be added to mainPanel");
    }

    @Test
    void setFrameFeatures() {
        // This method sets up the frame features. Some features like visibility might not be test-friendly
        window.setFrameFeatures();
        assertEquals(JFrame.EXIT_ON_CLOSE, window.getDefaultCloseOperation(), "Default close operation should be EXIT_ON_CLOSE");
        assertTrue(window.isVisible(), "Window should be visible after setFrameFeatures is called");
    }

}
