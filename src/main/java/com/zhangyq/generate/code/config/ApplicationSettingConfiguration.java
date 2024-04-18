package com.zhangyq.generate.code.config;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import com.zhangyq.generate.code.dialog.config.ApplicationSettingPanel;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ApplicationSettingConfiguration implements Configurable {
    private final ApplicationSettingPanel applicationSettingPanel = new ApplicationSettingPanel();

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "";
    }

    @Override
    public @Nullable JComponent createComponent() {
        ApplicationSetting instance = ApplicationSetting.getInstance();
        instance.setDefault();
        applicationSettingPanel.methodTemplate.setText(instance.methodTemplate);
        applicationSettingPanel.classTemplate.setText(instance.classTemplate);
        applicationSettingPanel.testUtilTemplate.setText(instance.testUtilTemplate);
        return applicationSettingPanel.getMainPanel();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        ApplicationSetting.getInstance().methodTemplate = applicationSettingPanel.methodTemplate.getText();
        ApplicationSetting.getInstance().classTemplate = applicationSettingPanel.classTemplate.getText();
        ApplicationSetting.getInstance().testUtilTemplate = applicationSettingPanel.testUtilTemplate.getText();
    }

    @Override
    public void reset() {
        ApplicationSetting instance = ApplicationSetting.getInstance();
        instance.reset();
        applicationSettingPanel.methodTemplate.setText(instance.methodTemplate);
        applicationSettingPanel.classTemplate.setText(instance.classTemplate);
        applicationSettingPanel.testUtilTemplate.setText(instance.testUtilTemplate);
    }
}
