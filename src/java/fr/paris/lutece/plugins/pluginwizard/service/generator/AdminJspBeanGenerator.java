/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.pluginwizard.service.generator;

import fr.paris.lutece.plugins.pluginwizard.business.model.BusinessClass;
import fr.paris.lutece.plugins.pluginwizard.business.model.Feature;
import fr.paris.lutece.plugins.pluginwizard.business.model.PluginModel;
import fr.paris.lutece.plugins.pluginwizard.service.ModelService;
import static fr.paris.lutece.plugins.pluginwizard.service.generator.Markers.MARK_BUSINESS_CLASS;
import static fr.paris.lutece.plugins.pluginwizard.service.generator.Markers.MARK_FEATURE_NAME;
import static fr.paris.lutece.plugins.pluginwizard.service.generator.Markers.MARK_FEATURE_RIGHT;
import static fr.paris.lutece.plugins.pluginwizard.service.generator.Markers.MARK_PLUGIN_MODEL;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * Admin Jsp Bean Generator
 */
public class AdminJspBeanGenerator extends AbstractGenerator
{
    private static final String PATH = "src/java/fr/paris/lutece/plugins/{plugin_name}/web/";
    private static final String SUFFIX_JSPBEAN = "JspBean.java";
    private String _strAbstractParentBeanTemplate;

    /**
     *
     * @param strParent
     */
    public void setAbstractParentBeanTemplate( String strParent )
    {
        _strAbstractParentBeanTemplate = strParent;
    }

    /**
     * {@inheritDoc }
     * @param pm 
     */
    @Override
    public Map generate( PluginModel pm )
    {
        HashMap map = new HashMap(  );

        for ( Feature feature : pm.getFeatures(  ) )
        {
            Collection<BusinessClass> listBusinessClasses = ModelService.getBusinessClassesByFeature( pm,
                    feature.getId(  ) );

            for ( BusinessClass business : listBusinessClasses )
            {
                String strFilename = business.getBusinessClassCapsFirst(  ) + SUFFIX_JSPBEAN;
                String strPath = getFilePath( pm, PATH, strFilename );
                String strSourceCode = getJspBeanCode( pm, feature.getPluginFeatureName(  ),
                        feature.getPluginFeatureRight(  ), business );
                map.put( strPath, strSourceCode );
            }

            String strPath = getFilePath( pm, PATH, feature.getPluginFeatureName(  ) + SUFFIX_JSPBEAN );
            String strSourceCode = getAbstractJspBeanCode( pm, feature.getPluginFeatureName(  ),
                    feature.getPluginFeatureRight(  ) );
            map.put( strPath, strSourceCode );
        }

        return map;
    }

    /**
     * Return JspBean code
     *
     * @param pluginModel The plugin model
     * @param strFeatureName The feature name
     * @param strFeatureRight The feature right
     * @param listBusinessClasses The list of business classes
     * @return the template The source code of the Jsp Bean
     */
    private String getJspBeanCode( PluginModel pluginModel, String strFeatureName, String strFeatureRight,
        BusinessClass business )
    {
        Map<String, Object> model = new HashMap<String, Object>(  );

        model.put( MARK_BUSINESS_CLASS, business );
        model.put( MARK_PLUGIN_MODEL, pluginModel );
        model.put( MARK_FEATURE_NAME, strFeatureName );
        model.put( MARK_FEATURE_RIGHT, strFeatureRight );

        HtmlTemplate template = AppTemplateService.getTemplate( getTemplate(  ), Locale.getDefault(  ), model );

        return template.getHtml(  );
    }

    /**
    * Return JspBean code
    *
    * @param pluginModel The plugin model
    * @param strFeatureName The feature name
    * @param strFeatureRight The feature right
    * @return the template The source code of the Jsp Bean
    */
    private String getAbstractJspBeanCode( PluginModel pluginModel, String strFeatureName, String strFeatureRight )
    {
        Map<String, Object> model = new HashMap<String, Object>(  );

        model.put( MARK_PLUGIN_MODEL, pluginModel );
        model.put( MARK_FEATURE_NAME, strFeatureName );
        model.put( MARK_FEATURE_RIGHT, strFeatureRight );

        HtmlTemplate template = AppTemplateService.getTemplate( _strAbstractParentBeanTemplate, Locale.getDefault(  ),
                model );

        return template.getHtml(  );
    }
}
