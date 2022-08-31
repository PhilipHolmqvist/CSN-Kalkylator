package com.example.application.views.list;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.text.NumberFormat;

@PageTitle("list")
@Route(value = "")
public class ListView extends VerticalLayout {

    private int counter = 0;
    private Label infoText;
    private final int fribelopp = 73825;
    private int summa = 73825;


    public ListView() {

        setSpacing(false);
        //Image img = new Image("images/empty-plant.png", "placeholder plant");
        //img.setWidth("200px");
        //add(img);


        add(new H2("CSN Fribelopp kalkylator"));
        add(new Paragraph("Hur mycket kan jag tjäna?"));

        TextField income = new TextField();
        income.getStyle().set("width", "12em");
        income.setLabel("Inkomst HT 2022");

        RadioButtonGroup<String> employmentType = new RadioButtonGroup<>();
        employmentType.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        employmentType.setLabel("Anställnings typ");
        employmentType.setItems("Fastanställd", "Timanställd");

        HorizontalLayout h1 = new HorizontalLayout(income, employmentType);
        h1.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        add(h1);

        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);
        Button button = new Button("Räkna ut belopp");
        add(button);
        /*
        Paragraph p = new Paragraph("Du kan tjäna: ");
        p.setHeight(10, Unit.PERCENTAGE);
        add(p);

         */

        add(new Paragraph("Du kan tjäna:"));

        infoText = new Label(myFormat.format(summa) + ":- kr. ");

        Icon confirmed = createIcon(VaadinIcon.CHECK, "Confirmed");
        confirmed.getElement().getThemeList().add("badge success");
        confirmed.setVisible(false);

        Icon cancelled = createIcon(VaadinIcon.CLOSE_SMALL, "Cancelled");
        cancelled.getElement().getThemeList().add("badge error");
        cancelled.setVisible(false);

        HorizontalLayout h2 = new HorizontalLayout(infoText, confirmed, cancelled);
        h2.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        add(h2);

        add(new Paragraph("Innan du maxat ditt fribelopp"));

        button.addClickListener(clickEvent -> {
            summa -= Integer.parseInt(income.getValue());
            Label sum = new Label(myFormat.format(summa) + ":- kr. ");

            if(summa >= 0) { //Man har tjänat mindre än fribeloppet.
                h2.replace(cancelled, confirmed);
                confirmed.setVisible(true);
                cancelled.setVisible(false);

            }else{
                h2.replace(confirmed, cancelled);
                cancelled.setVisible(true);
                confirmed.setVisible(false);
            }

            h2.replace(infoText, sum);
            infoText = sum;

        });

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    private com.vaadin.flow.component.icon.Icon createIcon(VaadinIcon vaadinIcon, String label) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        // Accessible label
        icon.getElement().setAttribute("aria-label", label);
        // Tooltip
        icon.getElement().setAttribute("title", label);
        return icon;
    }

}
