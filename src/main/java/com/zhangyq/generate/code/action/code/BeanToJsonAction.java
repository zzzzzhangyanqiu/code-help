package com.zhangyq.generate.code.action.code;

import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.zhangyq.generate.code.generator.value.JsonFileGenerator;
import com.zhangyq.generate.util.PluginUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhangyq01
 * @ClassName: BeanToJsonAction
 */
public class BeanToJsonAction extends AnAction {

    private static final NotificationGroup NOTIFICATIONGROUP = new NotificationGroup("CodeHelper.NotificationGroup", NotificationDisplayType.BALLOON, true);

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        if(Objects.isNull(psiFile)) {
            return;
        }
        Editor editor = e.getData(PlatformDataKeys.EDITOR);

        PsiElement element = PluginUtil.findElement(psiFile, editor.getCaretModel().getOffset());

        if(Objects.isNull(element)) {
            return;
        }

        PsiClass psiClass = PluginUtil.getContainingClass(element);

        if(Objects.isNull(psiClass)) {
            return;
        }
        Map<String, Object> fieldMap = Arrays.stream(psiClass.getAllFields()).collect(
            Collectors.toMap(field -> field.getName(),
                    field -> JsonFileGenerator.typeResolve(field.getType(), 0),
                    (a, b) -> b));

        //PsiTypesUtil.getDefaultValue()
        String message = "data as json already copied to clipboard";
        Notification success = NOTIFICATIONGROUP.createNotification(message, NotificationType.INFORMATION);
        Notifications.Bus.notify(success, e.getProject());

    }

    public BeanToJsonAction() {
        super();
        getTemplatePresentation().setText("Bean To Json");
    }

    /**
     * 只有java文件才能使用
     *
     * @see AnAction#update(AnActionEvent)
     */
    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        PluginUtil.update(e);
    }
}
