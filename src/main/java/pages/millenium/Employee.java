package pages.millenium;

import data.Users;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pages.BasePage;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.*;

import java.util.List;

/**
 * Created by bigdrop on 9/21/2018.
 */
public class Employee extends BasePage {

    public Employee(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    @FindBy(xpath = "//input[@value='New']")
    private Button newEmployeeBut;

    @FindBy(xpath = "//select[contains(@id, 'Location_lstAvailable')]")
    private Select selectLocationAvailable;

    @FindBy(xpath = "//input[@id='ctl00_Main_cntLocation_btnAdd']")
    private Button addLocationBut;

    @FindBy(id = "ctl00_Main_txtCode")
    private TextInput codeField;

    @FindBy(id = "ctl00_Main_txtFirstName")
    private TextInput firstNameField;

    @FindBy(id = "ctl00_Main_txtLastName")
    private TextInput lastNameField;

    @FindBy(id = "ctl00_Main_chkShowAppointment")
    private CheckBox chkShowAppointment;

    @FindBy(id = "ctl00_Main_chkShowTips")
    private CheckBox chkShowTips;

    @FindBy(id = "ctl00_Main_chkShowSchedules")
    private CheckBox chkShowTipschkShowSchedules;

    @FindBy(id = "ctl00_Main_rblSex_0")
    private Radio radioButSex;

    @FindBy(xpath = "//td[contains(text(), 'Massage Therapist')]/preceding-sibling::td")
    private CheckBox checkMassTher;

    @FindBy(xpath = "//a[contains(text(), 'Appts & Services')]")
    private Link appServiceLink;

    @FindBy(xpath = "//input[@value='Save']")
    private Button saveEmpBut;

    @FindBy(xpath = "//input[@value='Edit']")
    private Button editServBut;

    @Name("ArrayList of service checkbox")
    @FindBys( {@FindBy(xpath = "//input[contains(@id, 'Services')]")} )
    private List<HtmlElement> listServiceCheckBox;

    @Name("ArrayList of header service table")
    @FindBys( {@FindBy(css = ".headerrow td")} )
    private List<HtmlElement> listHeaderTableService;

    @Name("ArrayList of footer pagination item")
    @FindBys( {@FindBy(css = ".footerrow2 a")} )
    private List<HtmlElement> listFooterPaginItem;

    @Name("ArrayList of all td in table after checkbox")
    @FindBys( {@FindBy(css = "//input[contains(@id, 'Services')]//ancestor::td[@align and not (@class)]//following-sibling::td")} )
    private List<HtmlElement> lisTdAfterCheckbox;

    public void getCellFromAppTable(String headingColumn) {
        int indexColumn = 0;
        for (int i = 0; i < listHeaderTableService.size(); i++) {
            if(listHeaderTableService.get(i).getText().contains(headingColumn)){
                indexColumn = i;
                break;
            }
        }
        for (int i = 1; i < listFooterPaginItem.size(); i++) {
            if(listFooterPaginItem.get(i).getText().equals("...")) {
                listFooterPaginItem.get(i).click();
                i = 1;
            }
            for (int j = indexColumn; j < listServiceCheckBox.size(); j += (listServiceCheckBox.size() / listHeaderTableService.size())) {
                listServiceCheckBox.get(j).click();
            }
            listFooterPaginItem.get(i).click();
        }
    }

    public void createNewMaleTherapist(Users users) {
        newEmployeeBut.click();
        selectLocationAvailable.selectByValue("2");
        addLocationBut.click();
        type(codeField, users.getFirstName().toUpperCase());
        type(firstNameField, users.getFirstName());
        type(lastNameField, users.getLastName());
        checkMassTher.select();
        chkShowAppointment.select();
        chkShowTips.select();
        chkShowTipschkShowSchedules.select();
        radioButSex.selectByValue("1");
        saveEmpBut.click();
        appServiceLink.click();
        editServBut.click();
        getCellFromAppTable("Allowed?");
    }




}
