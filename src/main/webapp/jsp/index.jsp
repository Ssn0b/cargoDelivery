<%@ include file="header.jsp" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<body>
<div class="intro">
    <div class="container">
        <div class="intro__inner">
            <h2 class="intro__suptitle"><fmt:message key="index.lotsOfPoss" bundle="${lang}"/></h2>
            <h1 class="intro__title"><fmt:message key="index.welcome" bundle="${lang}"/></h1>
            <a class="btn" href="#"><fmt:message key="index.learnMore" bundle="${lang}"/></a>
        </div>
    </div>
</div>

<section class="section">
    <div class="container">
        <div class="section__header">
            <h3 class="section__suptitle"><fmt:message key="index.doWhatLove" bundle="${lang}"/></h3>
            <h2 class="section__title"><fmt:message key="index.storyAboutUs" bundle="${lang}"/></h2>
            <div class="section__text">
                <p><fmt:message key="index.text" bundle="${lang}"/></p>
            </div>
        </div>


        <div class="about">
            <div class="about__item">
                <div class="about__img">
                    <img src="${pageContext.request.contextPath}/img/image1.jpg" alt="">
                </div>
                <div class="about__text"><fmt:message key="index.superService" bundle="${lang}"/></div>
            </div>
            <div class="about__item">
                <div class="about__img">
                    <img src="${pageContext.request.contextPath}/img/image2.jpg" alt="">
                </div>
                <div class="about__text"><fmt:message key="index.superClients" bundle="${lang}"/></div>
            </div>
            <div class="about__item">
                <div class="about__img">
                    <img src="${pageContext.request.contextPath}/img/image3.jpg" alt="">
                </div>
                <div class="about__text"><fmt:message key="index.superCities" bundle="${lang}"/></div>
            </div>
        </div>
    </div>
</section>

<div class="statistic">
    <div class="container">

        <div class="stat">
            <div class="stat__item">
                <div class="stat__count">500</div>
                <div class="stat__text"><fmt:message key="index.stat1" bundle="${lang}"/>
                </div>
            </div>
            <div class="stat__item">
                <div class="stat__count">100%</div>
                <div class="stat__text"><fmt:message key="index.stat2" bundle="${lang}"/>
                </div>
            </div>
            <div class="stat__item">
                <div class="stat__count">20</div>
                <div class="stat__text"><fmt:message key="index.stat3" bundle="${lang}"/>
                </div>
            </div>
            <div class="stat__item">
                <div class="stat__count">1</div>
                <div class="stat__text"><fmt:message key="index.stat4" bundle="${lang}"/>
                </div>
            </div>
            <div class="stat__item">
                <div class="stat__count">5</div>
                <div class="stat__text"><fmt:message key="index.stat5" bundle="${lang}"/>
                </div>
            </div>
        </div>

    </div>
</div>

<section class="section">
    <div class="container">

        <div class="section__header">
            <h3 class="section__suptitle"><fmt:message key="index.weWorkWith" bundle="${lang}"/></h3>
            <h2 class="section__title"><fmt:message key="index.amazingServices" bundle="${lang}"/></h2>
        </div>

        <div class="services">
            <div class="services__item">
                <img class="services__icon" src="${pageContext.request.contextPath}/img/services/photography.png"
                     alt="">

                <div class="services__title"><fmt:message key="index.onTime" bundle="${lang}"/></div>
                <div class="services__text"><fmt:message key="index.stat2.1" bundle="${lang}"/></div>
            </div>
            <div class="services__item">
                <img class="services__icon" src="${pageContext.request.contextPath}/img/services/webdesign.png" alt="">

                <div class="services__title"><fmt:message key="index.manyLocations" bundle="${lang}"/></div>
                <div class="services__text"><fmt:message key="index.stat2.2" bundle="${lang}"/></div>
            </div>
            <div class="services__item">
                <img class="services__icon" src="${pageContext.request.contextPath}/img/services/creativity.png" alt="">

                <div class="services__title"><fmt:message key="index.comfort" bundle="${lang}"/></div>
                <div class="services__text"><fmt:message key="index.stat2.3" bundle="${lang}"/></div>
            </div>
        </div>

        <%--
                <hr>
        --%>

        <div class="services"
        ">
        <div class="services__item">
            <img class="services__icon" src="${pageContext.request.contextPath}/img/services/seo.png" alt="">

            <div class="services__title"><fmt:message key="index.selfImp" bundle="${lang}"/></div>
            <div class="services__text"><fmt:message key="index.stat2.4" bundle="${lang}"/></div>
        </div>
        <div class="services__item">
            <img class="services__icon" src="${pageContext.request.contextPath}/img/services/css-html.png" alt="">

            <div class="services__title"><fmt:message key="index.homeFeeling" bundle="${lang}"/></div>
            <div class="services__text"><fmt:message key="index.stat2.5" bundle="${lang}"/></div>
        </div>
        <div class="services__item">
            <img class="services__icon" src="${pageContext.request.contextPath}/img/services/digital.png" alt="">

            <div class="services__title"><fmt:message key="index.modernity" bundle="${lang}"/></div>
            <div class="services__text"><fmt:message key="index.stat2.6" bundle="${lang}"/></div>
        </div>
    </div>

    </div><!-- /.container -->
</section>

</body>
</html>
