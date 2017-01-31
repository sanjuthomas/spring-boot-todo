package com.example;

import java.util.Arrays;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class TodoLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	private final CheckBox done;
	private final TextField text;

	public TodoLayout(final Todo todo, final TodoChangeListener changeListener) {
		this.setWidth("100%");
		this.setSpacing(true);
		this.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

		this.done = new CheckBox();
		this.text = new TextField();
		this.text.setWidth("100%");
		this.text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		final FieldGroup fieldGroup = new FieldGroup(new BeanItem<>(todo));
		fieldGroup.setBuffered(false);
		fieldGroup.bindMemberFields(this);
		this.addComponents(this.done, this.text);
		this.setExpandRatio(this.text, 1);

		Arrays.asList(this.done, this.text).forEach(field -> {
			field.addValueChangeListener(change -> changeListener.todoChanged(todo));
		});
	}
}
