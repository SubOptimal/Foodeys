package eu.jugcologne.foodeys.services.memory;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.jugcologne.foodeys.services.AbstractService;
import eu.jugcologne.foodeys.services.api.Service;
import eu.jugcologne.foodeys.services.api.UnitService;

@RunWith(Arquillian.class)
public class DBUnitServiceTest {
    @Inject
    private UnitService unitService;

    @Deployment
    public static WebArchive createDeployment() {
	return ShrinkWrap.create(WebArchive.class, "test.war")
		.addClass(DBUnitService.class)
		.addClass(AbstractInMemoryService.class)
		.addClass(AbstractService.class)

		.addClass(UnitService.class)
		.addClass(Service.class)

		.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testIsDeployed() {
	Assert.assertNotNull(unitService);
    }
}