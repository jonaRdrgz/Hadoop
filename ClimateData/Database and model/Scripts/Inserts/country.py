hola = """Albania
Austria
Belarus
Belgium
Bosnia and Herzegovina
Bulgaria
Croatia
Czech Republic
Denmark
Estonia
Finland
Former Yugoslav Republic of Macedonia
France
Germany
Gibraltar
Greece
Hungary
Iceland
Ireland
Italy
Latvia
Lithuania
Luxembourg
Malta
Moldova
Netherlands
Norway
Poland
Portugal
Romania
Slovakia
Slovenia
Spain
Sweden
Switzerland
Ukraine
United Kingdom
Yugoslavia"""

hola =hola.split('\n')
for i in range (len(hola)):
	print ("""INSERT INTO Country(idCountry, descriptionCountry, idContinentCountry)\nVALUES (NULL, '"""+hola[i]+"""', 1);\n""")

hola = """Afghanistan
Armenia
Azerbaijan
Bahrain
Bangladesh
Brunei
Cambodia
China
Cyprus
Georgia
Hong Kong
India
Indonesia
Iran
Iraq
Israel
Japan
Jordan
Kazakhstan
Kuwait
Kyrgyzstan
Laos
Lebanon
Macau
Malaysia
Maldives
Mongolia
Myanmar
Nepal
North Korea
Oman
Pakistan
Philippines
Qatar
Russia
Saudi Arabia
Singapore
South Korea
Sri Lanka
Syria
Taiwan
Tajikistan
Thailand
Turkey
Turkmenistan
United Arab Emirates
Uzbekistan
Vietnam
Yemen"""

hola =hola.split('\n')
for i in range (len(hola)):
	print ("""INSERT INTO Country(idCountry, descriptionCountry, idContinentCountry)\nVALUES (NULL, '"""+hola[i]+"""', 2);\n""")
hola = """Algeria
Angola
Benin
Botswana
British Indian Ocean Territory
Burkina Faso
Burundi
Cameroon
Cape Verde
Central African Republic
Chad
Comoros
Congo
Côte d Ivoire
Democratic Republic of the Congo
Djibouti
Egypt
Equatorial Guinea
Ethiopia
Gabon
Ghana
Guinea
Guinea-Bissau
Kenya
Lesotho
Liberia
Libya
Madagascar
Malawi
Mali
Mauritania
Mauritius
Morocco
Mozambique
Namibia
Niger
Nigeria
Réunion
Rwanda
Saint Helena
São Tomé and Príncipe
Senegal
Seychelles
Sierra Leone
Somalia
South Africa
Sudan
Swaziland
Tanzania
The Gambia
Togo
Tunisia
Uganda
Western Sahara
Zambia
Zimbabwe"""

hola =hola.split('\n')
for i in range (len(hola)):
	print ("""INSERT INTO Country(idCountry, descriptionCountry, idContinentCountry)\nVALUES (NULL, '"""+hola[i]+"""', 3);\n""")
hola = """Antigua and Barbuda
Aruba
Bahamas
Barbados
Belize
Bermuda
British Virgin Islands
Canada
Cayman Islands
Costa Rica
Cuba
Dominica
Dominican Republic
El Salvador
Greenland
Grenada
Guadeloupe
Guam
Guatemala
Haiti
Honduras
Jamaica
Martinique
Mexico
Netherlands Antilles
Nicaragua
Panama
Puerto Rico
Saint Kitts and Nevis
Saint Lucia
Saint Pierre and Miquelon
Saint Vincent and the Grenadines
Trinidad and Tobago
United States
United States Minor Outlying Islands
US Virgin Islands"""

hola =hola.split('\n')
for i in range (len(hola)):
	print ("""INSERT INTO Country(idCountry, descriptionCountry, idContinentCountry)\nVALUES (NULL, '"""+hola[i]+"""', 4);\n""")
hola = """Argentina
Bolivia
Brazil
Chile
Colombia
Ecuador
Falkland Islands
French Guiana
Guyana
Paraguay
Peru
South Georgia and the South Sandwich Islands
Suriname
Uruguay
Venezuela"""

hola =hola.split('\n')
for i in range (len(hola)):
	print ("""INSERT INTO Country(idCountry, descriptionCountry, idContinentCountry)\nVALUES (NULL, '"""+hola[i]+"""', 5);\n""")
hola = """Australia
Christmas Island
Cook Islands
East Timor
Fiji
French Polynesia
Kiribati
Marshall Islands
Micronesia
Nauru
New Caledonia
New Zealand
Palau
Papua New Guinea
Samoa
Solomon Islands
Tonga
Tuvalu
Vanuatu"""

hola =hola.split('\n')
for i in range (len(hola)):
	print ("""INSERT INTO Country(idCountry, descriptionCountry, idContinentCountry)\nVALUES (NULL, '"""+hola[i]+"""', 6);\n""")
































