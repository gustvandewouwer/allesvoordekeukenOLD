package be.vdab.servlets.artikels;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.entities.FoodArtikel;
import be.vdab.entities.NonFoodArtikel;
import be.vdab.services.ArtikelService;
import be.vdab.util.StringUtils;

@WebServlet("/artikels/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/artikels/zoekenopnummer.htm?id=%d";
	private final transient ArtikelService artikelService = new ArtikelService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if (!Artikel.isNaamValid(naam)) {
			fouten.put("voornaam", "verplicht");
		}
		String aankoopprijsString = request.getParameter("aankoopprijs");
		BigDecimal aankoopprijs = null;
		if (StringUtils.isBigDecimal(aankoopprijsString)) {
			aankoopprijs = new BigDecimal(aankoopprijsString);
			if (!Artikel.isAankoopPrijsValid(aankoopprijs)) {
				fouten.put("aankoopprijs", "tik een positief getal of 0");
			}
		} else {
			fouten.put("aankoopprijs", "tik een positief getal of 0");
		}
		String verkoopprijsString = request.getParameter("verkoopprijs");
		BigDecimal verkoopprijs = null;
		if (StringUtils.isBigDecimal(verkoopprijsString)) {
			verkoopprijs = new BigDecimal(verkoopprijsString);
			if (!Artikel.isVerkoopprijsValid(verkoopprijs, aankoopprijs)) {
				fouten.put("verkoopprijs", "tik een positief getal of getal dat groter is dan de aankoopprijs");
			}
		} else {
			fouten.put("verkoopprijs", "tik een positief getal of 0");
		}

		int houdbaarheid = 0;
		int garantie = 0;
		String soort = request.getParameter("soort");
		if (soort == null) {
			fouten.put("soort", "maak een keuze");
		} else {
			switch (soort) {
			case "F":
				String houdbaarheidString = request.getParameter("houdbaarheid");
				if (StringUtils.isInt(houdbaarheidString)) {
					houdbaarheid = Integer.parseInt(houdbaarheidString);
					if (!FoodArtikel.isHoudbaarheidValid(houdbaarheid)) {
						fouten.put("houdbaarheid", "tik een positief getal");
					}
				} else {
					fouten.put("houdbaarheid", "tik een positief getal");
				}
				break;
			case "NF":
				String garantieString = request.getParameter("garantie");
				if (StringUtils.isInt(garantieString)) {
					garantie = Integer.parseInt(garantieString);
					if (!NonFoodArtikel.isGarantieValid(garantie)) {
						fouten.put("garantie", "tik een positief getal of 0");
					}
				} else {
					fouten.put("garantie", "tik een positief getal of 0");
				}
				break;
			default:
				fouten.put("soort", "maak een keuze");
			}
		}

		if (fouten.isEmpty()) {
			Artikel artikel;
			if ("F".equals(soort)) {
				artikel = new FoodArtikel(naam, aankoopprijs, verkoopprijs, houdbaarheid);
			} else {
				artikel = new NonFoodArtikel(naam, aankoopprijs, verkoopprijs, garantie);
			}
			artikelService.create(artikel);
			response.sendRedirect(
					response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath(), artikel.getId())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
