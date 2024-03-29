package wayc.backend.integration.shop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.shop.application.service.ShopService;
import wayc.backend.shop.domain.command.ShopRepository;

import static org.assertj.core.api.Assertions.*;

public class ShopServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopRepository shopRepository;

}
