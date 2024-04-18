package com.zhangyq.generate.code.config;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import com.zhangyq.generate.code.dialog.config.ApplicationSettingPanel;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ApplicationSettingConfiguration implements Configurable {
    private final ApplicationSettingPanel applicationSettingPanel = new ApplicationSettingPanel();
    private final ApplicationSetting instance = ApplicationSetting.getInstance();
    private boolean isModified;

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "";
    }

    @Override
    public @Nullable JComponent createComponent() {
        instance.setDefault();
        displayConfig();
        return applicationSettingPanel.getMainPanel();
    }

    @Override
    public boolean isModified() {
        isModified = !instance.isDefault(applicationSettingPanel);
        return isModified;
    }

    @Override
    public void apply() throws ConfigurationException {
        if(isModified) {
            saveConfig();
        }
    }

    private void saveConfig() {
        instance.methodTemplate = applicationSettingPanel.methodTemplate.getText();
        instance.classTemplate = applicationSettingPanel.classTemplate.getText();
        instance.testUtilTemplate = applicationSettingPanel.testUtilTemplate.getText();
    }

    private void displayConfig() {
        applicationSettingPanel.methodTemplate.setText(instance.methodTemplate);
        applicationSettingPanel.classTemplate.setText(instance.classTemplate);
        applicationSettingPanel.testUtilTemplate.setText(instance.testUtilTemplate);
    }

    @Override
    public void reset() {
        if (!isModified) {
            return;
        }
        instance.reset();
        displayConfig();
        isModified = false;
    }
}
