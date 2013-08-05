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

import fr.paris.lutece.plugins.pluginwizard.business.model.PluginModel;
import fr.paris.lutece.plugins.pluginwizard.business.model.Portlet;
import static fr.paris.lutece.plugins.pluginwizard.service.generator.Markers.*;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 *
 * Class generated the jsp files needed to manage portlets
 *
 */
public class PortletJspGenerator extends AbstractGenerator
{
    private static final String PATH = "webapp/jsp/admin/plugins/{plugin_name}/";
    private static final String EXT_JSP = ".jsp";
    private static String[] _prefix = { "ModifyPortlet", "DoModifyPortlet", "CreatePortlet", "DoCreatePortlet" };

    /**
     * {@inheritDoc }
     */
    @Override
    public Map generate( PluginModel pm )
    {
        HashMap map = new HashMap(  );

        for ( Portlet portlet : pm.getPortlets(  ) )
        {
            for ( int i = 0; i < _prefix.length; i++ )
            {
                String strPortlet = portlet.getPluginPortletTypeName(  );
                int nIndex = strPortlet.lastIndexOf( "_" );
                String strPortletFile = getPortletFileName( getFirstCaps( 
                            strPortlet.substring( 0, nIndex ).toLowerCase(  ) ), i );

                String strPath = getFilePath( pm, PATH, strPortletFile );

                String strSourceCode = getPortletJspFile( portlet, pm.getPluginName(  ), i );
                strSourceCode = strSourceCode.replace( "&lt;", "<" );
                strSourceCode = strSourceCode.replace( "&gt;", ">" );
                map.put( strPath, strSourceCode );
            }
        }

        return map;
    }

    /**
     * Fetches the name of the portlet jsp
     *
     * @param strPortletName the name of the portlet
     * @param nPortletJspFileType The type of jsp
     * @return The name of the jsp file
     */
    private String getPortletFileName( String strPortletName, int nPortletJspFileType )
    {
        return _prefix[nPortletJspFileType] + strPortletName + EXT_JSP;
    }

    /**
    * Gets the portlet Jsp File
    * @param portlet The portlet
    * @param strPluginName the plugin name
    * @param nPortletJspType The type of portlet
    * @return The source code of the portlet jsp
    */
    private String getPortletJspFile( Portlet portlet, String strPluginName, int nPortletJspType )
    {
        Map<String, Object> model = new HashMap<String, Object>(  );
        model.put( MARK_PORTLET, portlet );
        model.put( MARK_PLUGIN_NAME, strPluginName );
        model.put( MARK_PORTLET_JSP_TYPE, nPortletJspType );

        HtmlTemplate template = AppTemplateService.getTemplate( getTemplate(  ), Locale.getDefault(  ), model );

        return template.getHtml(  );
    }
}
